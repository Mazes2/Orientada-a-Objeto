// src/test/java/atividade8/PessoaTest.java

package atividade8;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PessoaTest {

    @Test
    public void testDizerOla() {
        Pessoa pessoa = new Pessoa();
        assertEquals("Olá, mundo!", pessoa.dizerOla());
    }
}
