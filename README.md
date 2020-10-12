[![Maven Central](https://img.shields.io/maven-central/v/io.github.nicolas-van/eventemitter)](https://search.maven.org/artifact/io.github.nicolas-van/eventemitter) [![Gradle Build](https://github.com/nicolas-van/eventemitter/workflows/Gradle%20Build/badge.svg)](https://github.com/nicolas-van/eventemitter/actions)

# eventemitter

A simple library implementing an event emitter in Java.

## Installation

See [the page on search.maven.org](https://search.maven.org/artifact/io.github.nicolas-van/eventemitter) for Gradle of Maven configuration per version.

## Usage

```java
// ExampleClass.java
package example;

import eventemitter.EventEmitter;
import lombok.Getter;
import lombok.NonNull;

public class ExampleClass {
    
    @Getter
    private final EventEmitter<String> statusChanged = new EventEmitter<>();
    
    @Getter
    @NonNull
    private String status = "";
    
    public void setStatus(String stat) {
        this.status = stat;
        this.getStatusChanged().trigger(stat);
    }
}
```

```java
// ExampleConsumer.java
package example;

public class ExampleConsumer {
    public static void main(String[] args) {
        ExampleClass e = new ExampleClass();
        
        e.getStatusChanged().addConsumer((s) -> {
            System.out.println("New status is " + s);
        });
        
        e.setStatus("waiting");
    }
}
```

## Javadoc

[Link to the javadoc.](https://nicolas-van.github.io/eventemitter/javadoc)

## License

[See the license file.](./LICENSE.md)
