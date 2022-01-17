package com.team303.diesel.autonomous.actions.processors;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import javax.tools.Diagnostic.Kind;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.team303.diesel.autonomous.actions.annotations.AutoAction;
import com.team303.diesel.autonomous.actions.serializable.ActionConstructorSerializable;
import com.team303.diesel.autonomous.actions.serializable.ActionSerializable;

import javax.lang.model.SourceVersion;

@SupportedAnnotationTypes("com.team303.diesel.autonomous.actions.annotations.*")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class ActionProcessor extends AbstractProcessor {

    private static Set<String> ACTION_EVENT_METHODS = new HashSet<>(
            Arrays.asList("isDone", "onRun", "beforeFirstRun", "afterFirstRun", "afterFinished"));

    private Map<TypeElement, ActionSerializable> actionClasses = new HashMap<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            var annotationName = annotation.toString();

            if (annotationName.equals("com.team303.diesel.autonomous.actions.annotations.AutoAction")) {
                Set<TypeElement> classes = new HashSet<>();

                for (var elem : annotatedElements) {
                    // Make sure not applied to non class elements
                    if (elem.getKind() != ElementKind.CLASS) {
                        processingEnv.getMessager().printMessage(Kind.ERROR,
                                "@AutoAction annotation can only be applied to classes", elem);
                        return false;
                    }
                    classes.add((TypeElement) elem);
                }

                for (var classElem : classes) {
                    ensureAnnotatedActionClass(classElem);
                }
            } else if (annotationName.equals("com.team303.diesel.autonomous.actions.annotations.ActionConstructor")) {
                for (var elem : annotatedElements) {
                    // Make sure not applied to non constructor elements
                    if (elem.getKind() != ElementKind.CONSTRUCTOR) {
                        processingEnv.getMessager().printMessage(Kind.ERROR,
                                "@ActionConstructor annotation can only be applied to constructors", elem);
                        return false;
                    }

                    TypeElement actionClass = (TypeElement) elem.getEnclosingElement();
                    AutoAction classAnnotation = actionClass.getAnnotation(AutoAction.class);

                    if (classAnnotation == null) {
                        processingEnv.getMessager().printMessage(Kind.ERROR,
                                "@ActionConstructor annotation can only be applied to constructors of Action classes decorated with the @AutoAction annotation!",
                                elem);
                        return false;
                    }

                    ensureAnnotatedActionClass(actionClass);

                    ExecutableElement constructor = (ExecutableElement) elem;

                    ActionSerializable action = actionClasses.get(actionClass);

                    if (action == null)
                        throw new RuntimeException("Action from map is null, this should never happen");

                    if (action.getConstructors().get(constructor) != null)
                        throw new RuntimeException("Duplicate constructor in action map, this should never happen");

                    action.getConstructors().put(constructor, new ActionConstructorSerializable());
                }
            } else if (annotationName.equals("com.team303.diesel.autonomous.actions.annotations.ActionInput")) {
                for (var elem : annotatedElements) {
                    // Make sure not applied to non parameter elements
                    if (elem.getKind() != ElementKind.PARAMETER
                            || elem.getEnclosingElement().getKind() != ElementKind.CONSTRUCTOR) {
                        processingEnv.getMessager().printMessage(Kind.ERROR,
                                "@ActionInput annotation can only be applied to constructor parameters", elem);
                        return false;
                    }

                    System.out.println(elem);
                    System.out.println(elem.getClass());
                    System.out.println(elem.asType());
                    System.out.println(elem.asType().getKind());
                    System.out.println(elem.asType().getClass());
                    System.out.println("======================================");
                }
            } else {
                processingEnv.getMessager().printMessage(Kind.ERROR,
                        String.format("Illegal annotation type \"%s\" passed to ActionProcessor", annotation),
                        annotation);
            }
        }

        if (annotations.size() < 1)
            return false;

        try {
            FileObject file = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, "", "diesel.json");
            try (Writer w = new OutputStreamWriter(file.openOutputStream(), "UTF-8")) {
                JsonObject jsonObj = new JsonObject();

                JsonArray actions = new JsonArray();

                for (var action : actionClasses.values())
                    actions.add(action.serialize());

                jsonObj.add("actions", actions);

                w.write(jsonObj.toString());
            }
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Kind.ERROR, "Failed to write service loader metadata file: " + e);
            throw new RuntimeException(e);
        }

        return true;
    }

    private void ensureAnnotatedActionClass(TypeElement actionClass) {
        // Check that class is a sub class of Action
        if (!actionClass.getSuperclass().toString().equals("com.team303.diesel.autonomous.actions.Action")) {
            processingEnv.getMessager().printMessage(Kind.ERROR,
                    "@AutoAction annotation can only be applied to sub-classes of the Action class",
                    actionClass);
        }

        // Check that all overridden event methods have not expanded the access to
        // public
        for (var elem : actionClass.getEnclosedElements()) {
            if (elem.getKind() != ElementKind.METHOD)
                continue;

            ExecutableElement method = (ExecutableElement) elem;
            if (!ACTION_EVENT_METHODS.contains(method.getSimpleName().toString()))
                continue;

            if (!method.getModifiers().contains(Modifier.PROTECTED)) {
                processingEnv.getMessager().printMessage(Kind.ERROR,
                        String.format(
                                "Action Classes cannot widen the access of event methods like \"%s()\"",
                                method.getSimpleName().toString()),
                        method);
            }
        }

        // Passed all Checks, add to actionClass
        AutoAction elemAnnotation = actionClass.getAnnotation(AutoAction.class);
        String actionName = elemAnnotation.value();

        for (Map.Entry<TypeElement, ActionSerializable> entry : actionClasses.entrySet()) {
            var action = entry.getValue();
            var elem = entry.getKey();

            if (action.getName().equals(actionName) && elem != actionClass)
            processingEnv.getMessager().printMessage(Kind.ERROR,
                        String.format("Action with name \"%s\" already exists (%s)!", actionName,
                                action.getClassPath()),
                                actionClass);
        }

        actionClasses.putIfAbsent(actionClass, new ActionSerializable(actionClass.toString(), actionName));
    }
}