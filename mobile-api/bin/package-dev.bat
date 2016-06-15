@echo off
echo [INFO] Package the war in target dir.

cd %~dp0
cd ..
call mvn clean package -Dmaven.test.skip=true -Pdev
cd bin
pause