[![Build Status](https://api.travis-ci.org/namumba22/react.svg?branch=master)](https://travis-ci.org/namumba22/react)

# Eureka example
React sample.

## Testing the app

mvn clean install

bash-3.2$ ./target/akka-0.1-SNAPSHOT.jar /tmp/README.txt

### Components

                |--------------------------------|
                |     a root(the Main class )    |
                |--------------------------------|
                              ^
                              |
                    |------------------|
                    | SplitterObserver |
                    |------------------|
                       ^      ^                   ^                    ^
                      /       |                   \                     \
    |------------------|   |------------------|   |------------------||------------------|
    | SplitterObserver |   | WordCounter      |   | LongestWord      || ReverseWord      |
    |------------------|   |------------------|   |------------------||------------------|
                              ^
                              |
                            |------------------|
                            | printer
                            |------------------|








## Authors

* **Andy** - *Initial work* - [PurpleBooth](https://github.com/namumba22/)

## Cation




