package annotations;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

@SuppressWarnings("deprecation")
public class InterfaceExtractorProcessor implements AnnotationProcessor {
	private final AnnotationProcessorEnvironment ape;
	private ArrayList<MethodDeclaration> interfaceMethods = new ArrayList<>();
	
	public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
		ape = env;
	}

	@Override
	public void process() {
		for(TypeDeclaration typeDec : ape.getSpecifiedTypeDeclarations()) {
			ExtractInterface annot = typeDec.getAnnotation(ExtractInterface.class);
			if(annot == null) 
				break;
			for(MethodDeclaration m : typeDec.getMethods()) {
				if(m.getModifiers().contains(Modifier.PUBLIC) &&
						!(m.getModifiers().contains(Modifier.STATIC)))
					interfaceMethods.add(m);
			}
			if(interfaceMethods.size() > 0) {
				try {
					PrintWriter writer = ape.getFiler().createSourceFile(annot.value());
					writer.println("package " + typeDec.getPackage().getQualifiedName() + ";");
					writer.println("public interface " + annot.value() + " {");
					for(MethodDeclaration m : interfaceMethods) {
						writer.print("  public ");
						writer.print(m.getReturnType());
						writer.print(m.getSimpleName() + "(");
						int i = 0;
						for(ParameterDeclaration p : m.getParameters()) {
							writer.print(p.getType() + " " + p.getSimpleName());
							if(++i < m.getParameters().size()) 
								writer.print(", ");
						}
						writer.println(");");
					} 
					writer.println("}");
					writer.close();
				} catch (IOException e) {
					throw new RuntimeException();
				}
			}
		}
	}
	
}
