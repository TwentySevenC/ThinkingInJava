package annotations;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

@SuppressWarnings("deprecation")
public class InterfaceExtractorProcessorFactory implements AnnotationProcessorFactory {

	@Override
	public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> arg0,
			AnnotationProcessorEnvironment arg1) {
		return new InterfaceExtractorProcessor(arg1);
	}

	@Override
	public Collection<String> supportedAnnotationTypes() {
		return Collections.singleton("annotations.ExtractInterface");
	}

	@Override
	public Collection<String> supportedOptions() {
		return Collections.emptySet();
	}

}
