#!/bin/bash

Xvfb :99 &
export addr="127.0.0.1:9090"

/Katalon_Studio_Linux_64-5.7.1/katalon -noSplash  -runMode=console -projectPath="$(pwd)/agendarctest/agendarctest.prj" -retry=1 -testSuitePath="Test Suites/agendarctestsuite" -executionProfile="default" -browserType="Chrome (headless)" -Djava.awt.headless