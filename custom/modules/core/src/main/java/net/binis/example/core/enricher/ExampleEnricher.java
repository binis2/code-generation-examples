package net.binis.example.core.enricher;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.comments.LineComment;
import lombok.extern.slf4j.Slf4j;
import net.binis.codegen.enrich.handler.base.BaseEnricher;
import net.binis.codegen.generation.core.interfaces.PrototypeDescription;

import static net.binis.codegen.generation.core.EnrichHelpers.block;

@Slf4j
public class ExampleEnricher extends BaseEnricher {
    @Override
    public void enrich(PrototypeDescription<ClassOrInterfaceDeclaration> description) {
        log.info("Enriching {}", description.getInterfaceName());
        description.getInterface().getParentNode().ifPresent(node -> node.addOrphanComment(new LineComment("Example enriching!")));

        description.getInterface().addMethod("helloWorld").setBody(null);
        description.getImplementation().addMethod("helloWorld", Modifier.Keyword.PUBLIC)
                .setBody(block("{ example = \"Hello World!\"; }"));
    }

    @Override
    public int order() {
        return -100;
    }
}
