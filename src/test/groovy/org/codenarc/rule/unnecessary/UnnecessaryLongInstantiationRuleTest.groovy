/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codenarc.rule.unnecessary

import org.codenarc.rule.AbstractRuleTestCase
import org.codenarc.rule.Rule

/**
 * Tests for UnnecessaryLongInstantiationRule
 *
 * @author Hamlet D'Arcy
 * @version $Revision: 329 $ - $Date: 2010-04-29 04:20:25 +0200 (Thu, 29 Apr 2010) $
 */
class UnnecessaryLongInstantiationRuleTest extends AbstractRuleTestCase {

    void testRuleProperties() {
        assert rule.priority == 2
        assert rule.name == "UnnecessaryLongInstantiation"
    }

    void testSuccessScenario() {
        final SOURCE = '''
            assert 42L == foo()
            assert 42L == new Long([] as char[])
            assert 42L == new Long("42", 10)
        '''
        assertNoViolations(SOURCE)
    }

    void testStringConstructor() {
        final SOURCE = '''
            new Long("42")
        '''
        assertSingleViolation(SOURCE, 2, 'new Long("42")', "Can probably be rewritten as 42L")
    }

    void testLongConstructor() {
        final SOURCE = '''
            new Long(42L)
        '''
        assertSingleViolation(SOURCE, 2, 'new Long(42L)', "Can probably be rewritten as 42L")
    }

    protected Rule createRule() {
        new UnnecessaryLongInstantiationRule()
    }
}