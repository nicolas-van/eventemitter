![Maven Central](https://img.shields.io/maven-central/v/io.github.nicolas-van/eventemitter)

# eventemitter

A simple library implementing an event emitter in Java.

## Installation

### Gradle

```groovy
implementation 'io.github.nicolas-van:eventemitter:0.0.9'
```

### Maven

```xml
<dependency>
  <groupId>io.github.nicolas-van</groupId>
  <artifactId>eventemitter</artifactId>
  <version>0.0.9</version>
  <type>pom</type>
</dependency>
```

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
        this.statusChanged.trigger(stat);
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
