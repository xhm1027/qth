@echo off
call mvn clean install -Dmaven.test.skip=true
call mvn eclipse:clean eclipse:eclipse -U -DdownloadSources=true -DdownloadJavadocs=false
@pause