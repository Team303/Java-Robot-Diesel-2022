package frc.robot.annotations.processing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.tools.Diagnostic;
import javax.lang.model.SourceVersion;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes("frc.robot.annotations.ActionEvent")
@SupportedSourceVersion(SourceVersion.RELEASE_11)
public class ActionProcessor extends AbstractProcessor {

	private static final ArrayList<String> PROTECTED_NAMES = new ArrayList<>(
			Arrays.asList("isDone", "onRun", "beforeFirstRun", "afterFirstRun", "afterFinished"));

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		for (TypeElement annotation : annotations) {
			Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

			Map<Boolean, List<Element>> annotatedMethods = annotatedElements.stream().collect(
					Collectors.partitioningBy(
							element -> ((ExecutableType) element.asType()).getParameterTypes().size() == 1
									&& PROTECTED_NAMES.contains(element.getSimpleName().toString())));

		
			for (Element el : annotatedElements) {
				// Cast is safe since TypeKind is checked before this
				// ExecutableType exec = (ExecutableType) el.asType();

				System.out.println(el.getEnclosingElement());
				// final TypeElement typeEl = (TypeElement)el;

				// final TypeMirror parent = typeEl.getSuperclass();
				// if(parent.getKind().equals(TypeKind.NONE) && parent instanceof NoType) continue;

				
			}

			List<Element> correctMethods = annotatedMethods.get(true);
			List<Element> otherMethods = annotatedMethods.get(false);

			otherMethods.forEach(element -> processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
					"@ActionEvent must be applied to an an Event method in an action class method "
							+ "with a single argument",
					element));

			if (correctMethods.isEmpty()) {
				continue;
			}
		}

		return false;
	}
}