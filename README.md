girder
======

Girder is a toy framework for building HTTP APIs, built on top of [http4s](https://http4s.org/).

Motivation
----------

Girder grew out of me not really liking any of the framework options out there, but also not being able to clearly articulate why. My main work experience has been with [Spring Boot]() and [Play Framework](), both of which irked me in different ways.

While I think a framework in some sense is necessary for a working programmer -- you don't want to constantly be restarting from scratch -- it's a legitimate question whether that framework needs to be a pre-built one like Play or Spring. Girder is my exploration to see how much up-front work would be required to get a reusable set of components for building an HTTP API that works the way I want it to work.

Features
--------

A brief list of the things that I'm looking for:

- Endpoints that are just functions
- Structured logging
- Standardized, JSON-encoded error messaging
- Unique request ids
- Straightforward serialization
- Authentication / Authorization
- Basic security headers (CORS, etc.)
