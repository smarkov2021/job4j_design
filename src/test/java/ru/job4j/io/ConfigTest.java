package ru.job4j.io;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isNull();
    }

    @Test
    void whenFileContainsEmptyLines() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isNull();
    }

    @Test
    void whenFileContainsEmptyKey() {
        String path = "./data/file_with_empty_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> config.load()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenFileContainsOnlyComment() {
        String path = "./data/comment.properties";
        Config config = new Config(path);
        Assertions.assertThatCode(() -> config.load()).doesNotThrowAnyException();

    }
}