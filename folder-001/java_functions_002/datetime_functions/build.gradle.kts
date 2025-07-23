plugins {
    `java-library`
    `maven-publish`
    signing
}

group = "io.github.peterburbery"
version = "1.0.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar>().configureEach {
    archiveBaseName.set("datetime_functions")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            pom {
                name.set("datetime_functions")
                description.set("DateTime utilities from java_functions_002")
                url.set("https://github.com/PeterCullenBurbery/java-functions-002")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("peterburbery")
                        name.set("Peter Cullen Burbery")
                        email.set("peter.cullen.burbery@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/PeterCullenBurbery/java-functions-002.git")
                    developerConnection.set("scm:git:ssh://github.com:PeterCullenBurbery/java-functions-002.git")
                    url.set("https://github.com/PeterCullenBurbery/java-functions-002")
                }
            }
        }
    }

    repositories {
        mavenLocal()
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}
