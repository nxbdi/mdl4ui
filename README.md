[![Build Status](https://buildhive.cloudbees.com/job/lesfurets/job/mdl4ui/badge/icon)](https://buildhive.cloudbees.com/job/lesfurets/job/mdl4ui/)

mdl4ui
======

UI meta modeling framework based on java annotation and APT.
This project will show patterns and architecture to solve classical UI issues using a modeling approach:

* Complex UI form with more than 100 fields described in a model
* Linked form fields (dependencies)
* Conditional field display
* Dynamic content for lists, radio lists and combo boxes
* Dynamic range for numerical values and dates
* Incremental validation based on user inputs and navigation
* AJAX application target
* Slow JavaScript runtime for mobile and old Microsoft browsers


Session description:
--------------------

We work a long time to reverse engineer some complex UI forms of our insurance aggregator web site. We provide some models that explain all the dependencies between UI fields (with various semantics) and start to imagine some new design to rewrite a new generation of UI.

We decide to never lose again that knowledge and choose to translate that model as a UI meta model written in java. The model is essentially based on enumerations, annotations and relations between various literals. We translate the direct dependencies between fields with the same pattern.

The adventure was still at the beginning:

It was quickly necessary to visualize the field graph to understand cycle and to fix dependencies issues: we process the UI java model to export some XMI and display in a UML modeler. Quickly we start to validate the model dependencies during the JAVA compilation and we generate statically the set of deep dependencies for each field to improve the runtime performances and avoid graph cycles.

We start to implement the UI using a 'pure' MVC pattern to link the widget and the data model: each widget is instantiated dynamically at runtime based on the UI meta model and we plug the MVC glue between the widget and the data model with some injection patterns. The injection is statically resolved to target AJAX runtimes. In the same way, we inject labels, placeholders, helps, tips and other i18n text resources.

The rework of our forms was progressing quickly and we decide to add some layout information at the UI meta model level: it aggregates fields together in screens, blocks and groups. The structure allows checking the topology of the graph during the compilation: the layout is compliant with the dependency model of fields.

Actually we are experimenting some AB testing using multiple permutations of our field model to optimize the user experience and our conversion rate.

Our project is available on github with a full sample and all the tooling. We will show patterns, code and demo during the presentation.


The session was presented previously at JavaOne 2012 and various Java User Group in France, focused on i18n and labeling issues. We will mainly show here the UI modeling side of the project and some quick explanations on the UI labeling.

https://oracleus.activeevents.com/connect/sessionDetail.ww?SESSION_ID=11234

Session speakers:
-----------------

### Gilles Di Guglielmo
 Gilles is a 10+ software developer working for various software vendors (ILOG, PrimaSolution, Courtanet) : graphic 2D library, rules engine, J2EE service platform, domain model code generation. He is currently software architect for the insurance aggregator web site LesFurets.com. He loves to feel the fresh air of San Francisco and Anvers. He was speaking at JavaOne, Devoxx and some Java User Groups in France.

### Julien Baudry
 Julien is a senior developer at the insurance aggregator web site LesFurets.com. He also worked for Prima Solutions insurance software vendor. He has 5+ years¡¯ experience of Java design & coding experience, Model Driven Architecture, code generation, J2EE & Web Framework.
