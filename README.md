
# eventemitter

A simple library implementing an event emitter in Java.

[Link to the javadoc.](https://nicolas-van.github.io/eventemitter/javadoc)

## Installation

### Gradle

```
repositories {
    jcenter()
}

dependencies {
  implementation 'io.github.nicolas-van:eventemitter:0.0.6'
}
```

### Maven

```
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
