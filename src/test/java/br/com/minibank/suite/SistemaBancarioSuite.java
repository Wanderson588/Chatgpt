package br.com.minibank.suite;

import br.com.minibank.BancoTest;
import br.com.minibank.ClienteTest;
import br.com.minibank.ContaTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ClienteTest.class, ContaTest.class, BancoTest.class})
public class SistemaBancarioSuite {
}
