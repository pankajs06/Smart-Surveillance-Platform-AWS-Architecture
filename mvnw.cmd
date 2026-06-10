@echo off
setlocal
set scriptdir=%~dp0
powershell -NoProfile -ExecutionPolicy Bypass -File "%scriptdir%mvnw.ps1" %*
endlocal
