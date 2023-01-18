package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }
    @Test
    void checkParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }
    @Test
    void checkValidateDoesNotContainEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("dd"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: dd does not contain the symbol \"=\"");
    }
    @Test
    void checkValidateStartsWithEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=dd"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: =dd does not contain a key");
    }
    @Test
    void checkValidateEndsWithEqual() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("dd="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: dd= does not contain a value");
    }
}