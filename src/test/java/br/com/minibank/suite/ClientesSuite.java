package br.com.minibank.suite;

import br.com.minibank.ClienteTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ClienteTest.class})
public class ClientesSuite {
}
