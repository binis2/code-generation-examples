package net.binis.example.core.enricher;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.comments.LineComment;
import lombok.extern.slf4j.Slf4j;
import net.binis.codegen.enrich.handler.base.BaseEnricher;
import net.binis.codegen.generation.core.interfaces.PrototypeDescription;

@Slf4j
public class ExampleEnricher extends BaseEnricher {
    @Override
    public void enrich(PrototypeDescription<ClassOrInterfaceDeclaration> description) {
        log.info("Enriching {}", description.getInterfaceName());
        description.getIntf().getParentNode().ifPresent(node -> node.addOrphanComment(new LineComment("Example enriching!")));

        description.getIntf().addMethod("helloWorld").setBody(null);
        description.getSpec().addMethod("helloWorld", Modifier.Keyword.PUBLIC)
                .setBody(description.getParser()
                        .parseBlock("{ example = \"Hello World!\"; }").getResult().get());
    }

    @Override
    public int order() {
        return -100;
    }
}
