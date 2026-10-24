@echo off
if exist "%~dp0..\..\..\Downloads\maven\bin\mvn.cmd" (
    call "%~dp0..\..\..\Downloads\maven\bin\mvn.cmd" %*
) else if exist "C:\Users\devil\Downloads\maven\bin\mvn.cmd" (
    call "C:\Users\devil\Downloads\maven\bin\mvn.cmd" %*
) else (
    call mvn %*
)
