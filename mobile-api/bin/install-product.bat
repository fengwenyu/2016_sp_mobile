@echo off
echo [INFO] Package the war in target dir.

cd %~dp0
cd ..
call mvn clean install -Dmaven.test.skip=true -Pproduct
cd bin
pause