[![License](https://img.shields.io/badge/license-Apache--2.0-brightgreen.svg)](LICENSE)

# Runway

`runway` is a simple Play Framework deployment tool.

## Prerequisites and Installation

Follow the following steps in order to build and run from source:

  1. Clone the repository from GitHub either via git command-line or git GUI (e.g. SmartGit, SourceTree, TortoiseGit, etc.)
  1. Switch to the application directory (e.g. `cd runway`)
  1. Run `sbt -mem 4096 -jvm-debug 9999` to allocate 4GB of memory and switch to the SBT console in debug mode
  1. Run `update` to pull down the required dependencies
  1. (Optional) Configure the solution for your favorite IDE:
     * IntelliJ IDEA: [Import as a Play Framework project](https://confluence.jetbrains.com/display/IntelliJIDEA/Play+Framework+2.0)
     * Eclipse: Run `eclipse`
  1. Run `test` to start the test runner and ensure all tests are passing
  1. Run `run` to start `runway` in [http://localhost:9000](http://localhost:9000)
  1. (Optional) Configure your IDE for debugging, refer to [Setting up your preferred IDE](https://www.playframework.com/documentation/2.3.x/IDE)
     in Play Framework documentation

## Development Environment

The following is the required development environment setup.

### Develop and Compile Dependencies

The following binaries / libraries need to be installed in order to compile the web application:

  * Java SDK 1.8, either [Azul Zulu 8](http://www.azul.com/downloads/zulu/) (preferred), or [Oracle Java SDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
  * [SBT 0.13.13+](http://www.scala-sbt.org/download.html)
  * [Scala 2.11.x](http://www.scala-lang.org/download/all.html)

## Contributing

We follow the "[fork-and-pull](https://help.github.com/articles/about-pull-requests/)" Git workflow.

  1. Commit changes to a branch (use `snake_case` convention):
     - For technical task / chores, use `chore/` prefix followed by the short description, e.g. `chore/do_this_chore`
     - For new features, use `feature/` prefix followed by the feature name, e.g. `feature/feature_name`
     - For bug fixes, use `fix/` prefix followed by the short description, e.g. `fix/fix_this_bug`
     - For hotfixes, use `hotfix/` prefix followed by the short description, e.g. `hotfix/config_update`
  1. Rebase or merge from `master`
  1. Submit a PR against `master` with your changes

Please read [CONTRIBUTING](CONTRIBUTING.md) for more details.
