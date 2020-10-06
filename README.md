
# eventemitter

A simple library implementing an event emitter in Java.

[Link to the javadoc.](https://nicolas-van.github.io/eventemitter/javadoc)

## Installation

### Gradle

```groovy
repositories {
    jcenter()
}

dependencies {
  implementation 'io.github.nicolas-van:eventemitter:0.0.6'
}
```

### Maven

```xml
<profiles>
  <profile>
    <repositories>
      <repository>
        <snapshots>
          <enabled>
            false
          </enabled>
        </snapshots>
        <id>
          bintray-nicolas-van-maven
        </id>
        <name>
          bintray
        </name>
        <url>
          https://dl.bintray.com/nicolas-van/maven
        </url>
      </repository>
    </repositories>
    <pluginRepositories>
      <pluginRepository>
        <snapshots>
          <enabled>
            false
          </enabled>
        </snapshots>
        <id>
          bintray-nicolas-van-maven
        </id>
        <name>
          bintray-plugins
        </name>
        <url>
          https://dl.bintray.com/nicolas-van/maven
        </url>
      </pluginRepository>
    </pluginRepositories>
    <id>
      bintray
    </id>
  </profile>
</profiles>
<activeProfiles>
  <activeProfile>
    bintray
  </activeProfile>
</activeProfiles>

<dependencies>
  <dependency>
    <groupId>io.github.nicolas-van</groupId>
    <artifactId>eventemitter</artifactId>
    <version>0.0.6</version>
    <type>pom</type>
  </dependency>
</dependencies>
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
    private EventEmitter<String> statusChanged = new EventEmitter<>();
    
    @Getter
    @NonNull
    private String status = "";
    
    public void setStatus(String stat) {
        if (!this.status.equals(stat)) {
            this.status = stat;
            this.statusChanged.trigger(stat);
        }
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

## License

[See the license file.](./LICENSE.md)
