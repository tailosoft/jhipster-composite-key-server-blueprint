# generator-jhipster-composite-key-server
[![NPM version][npm-image]][npm-url] [![Build Status][travis-image]][travis-url] [![Dependency Status][daviddm-image]][daviddm-url]
> JHipster blueprint, this jhipster blueprint allows to generate server code with composte key using custom annotations

# Introduction

This is a [JHipster](https://www.jhipster.tech/) blueprint, that is meant to be used in a JHipster application.

# Prerequisites

As this is a [JHipster](https://www.jhipster.tech/) blueprint, we expect you have JHipster and its related tools already installed:

- [Installing JHipster](https://www.jhipster.tech/installation/)

# Installation

## With NPM

To install this blueprint:

```bash
npm install -g generator-jhipster-composite-key-server
```

To update this blueprint:

```bash
npm update -g generator-jhipster-composite-key-server
```

## With Yarn

To install this blueprint:

```bash
yarn global add generator-jhipster-composite-key-server
```

To update this blueprint:

```bash
yarn global upgrade generator-jhipster-composite-key-server
```

# Usage

To use this blueprint, run the below command

```bash
jhipster --blueprint composite-key-server
```


## Running local Blueprint version for development

During development of blueprint, please note the below steps. They are very important.

1. Link your blueprint globally 

Note: If you do not want to link the blueprint(step 3) to each project being created, use NPM instead of Yarn as yeoman doesn't seem to fetch globally linked Yarn modules. On the other hand, this means you have to use NPM in all the below steps as well.

```bash
cd composite-key-server
npm link
```

2. Link a development version of JHipster to your blueprint (optional: required only if you want to use a non-released JHipster version, like the master branch or your own custom fork)

You could also use Yarn for this if you prefer

```bash
cd generator-jhipster
npm link

cd composite-key-server
npm link generator-jhipster
```

3. Create a new folder for the app to be generated and link JHipster and your blueprint there

```bash
mkdir my-app && cd my-app

npm link generator-jhipster-composite-key-server
npm link generator-jhipster (Optional: Needed only if you are using a non-released JHipster version)

jhipster -d --blueprint composite-key-server

```

# License

Apache-2.0 © [Youssef El Houti](https://elhouti.com)


[npm-image]: https://img.shields.io/npm/v/generator-jhipster-composite-key-server.svg
[npm-url]: https://npmjs.org/package/generator-jhipster-composite-key-server
[travis-image]: https://travis-ci.org/yelhouti/generator-jhipster-composite-key-server.svg?branch=master
[travis-url]: https://travis-ci.org/yelhouti/generator-jhipster-composite-key-server
[daviddm-image]: https://david-dm.org/yelhouti/generator-jhipster-composite-key-server.svg?theme=shields.io
[daviddm-url]: https://david-dm.org/yelhouti/generator-jhipster-composite-key-server

# Contributing
## testing
to test your changes, just run `npm test`, this will check that generated code matches matches the in `test/samples/`

If you change the sample project code, in that folder run: `./gradlew test -x webpack` to make sure your changes don't break generated code.

You can also run e2e tests using the project: https://github.com/elhoutico/jhipster-primeng-blueprint which support composite keys (change protractor config to use server on localhost:8080 instead of localhost:9000)
first start the sample project: `./gradlew -x webpack`
