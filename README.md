# EasyAdapter

## Description

A adapter for RecyclerView. It support add a header and footer just like ListView. It's easy to implement. User only need to focus on the onCreateViewHolder, onBindViewHolder and getViewType().


## How to use

### Gradle
Step 1: Add it in your root build.gradle at the end of repositories:

    allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
	
	
Step 2: Add the dependency
    
    dependencies {
		compile 'com.github.User:Repo:Tag'
	}
	
### Maven

Step 1:

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

Step 2: Add the dependency

	<dependency>
	    <groupId>com.github.User</groupId>
	    <artifactId>Repo</artifactId>
	    <version>Tag</version>
	</dependency>
