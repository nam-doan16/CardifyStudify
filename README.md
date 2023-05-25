[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/a1SE4wKh)
# 3500 PA 01 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-01-Summarize-This-c8275591b4eb43df9f56dbae881f2423) 

It include several additional tools:
1. Gradle Build Automation
1. JaCoCo for Test Coverage
1. CheckStyle for Code Style Checks (Using the custom [cs3500 check file](./config/checkstyle/cs3500-checkstyle.xml)) 

TAs who are grading:
* My program accepts both SR files with and without METADATA,
  * For SR files without metadata, all questions stored will be set default as hard, then the file will be overwritten
    with the METADATA
  * For SR files with metadata, it'll read it as normal and overwrite as normal