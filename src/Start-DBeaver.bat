@echo off
setlocal EnableDelayedExpansion

title OCI Bastion - DBeaver

REM ============================================================
REM CONFIGURATION
REM ============================================================

set "BASTION_ID=ocid1.bastion.oc1.ap-mumbai-1.amaaaaaaqcmgvqyaooerhwdp2njpxauhv4k5kfgkhwp6dwpybmhk5ubz2y7q"

set "TARGET_IP=10.0.1.57"
set "TARGET_PORT=3306"

set "LOCAL_IP=127.0.0.1"
set "LOCAL_PORT=3307"

set "SSH_KEY=%USERPROFILE%\root"
set "SSH_PUBLIC_KEY=%USERPROFILE%\root.pub"

set "BASTION_HOST=host.bastion.ap-mumbai-1.oci.oraclecloud.com"

REM ============================================================
REM CREATE SESSION
REM ============================================================

cls

echo.
echo ============================================================
echo                 OCI BASTION - DBEAVER
echo ============================================================
echo.
echo Creating new Bastion session...
echo.

set "SESSION_ID="

for /f "delims=" %%i in ('
    oci bastion session create-port-forwarding
    --bastion-id "%BASTION_ID%"
    --target-private-ip "%TARGET_IP%"
    --target-port %TARGET_PORT%
    --ssh-public-key-file "%SSH_PUBLIC_KEY%"
    --session-ttl 10800
    --query "data.id"
    --raw-output
') do (
    set "SESSION_ID=%%i"
)

if "!SESSION_ID!"=="" (
    echo.
    echo ERROR: Could not create Bastion session.
    echo.
    pause
    exit /b 1
)

echo.
echo ============================================================
echo              NEW SESSION CREATED
echo ============================================================
echo.
echo Session OCID:
echo.
echo !SESSION_ID!
echo.
echo ============================================================
echo.

REM ============================================================
REM COPY SESSION ID TO CLIPBOARD
REM ============================================================

echo !SESSION_ID! | clip

echo Session OCID copied to clipboard.
echo.

REM ============================================================
REM GIVE OCI A MOMENT TO INITIALIZE
REM ============================================================

echo Waiting 10 seconds for Bastion initialization...
timeout /t 10 /nobreak >nul

REM ============================================================
REM START SSH
REM ============================================================

:SSH_RETRY

cls

echo.
echo ============================================================
echo                 STARTING SSH TUNNEL
echo ============================================================
echo.
echo Session:
echo !SESSION_ID!
echo.
echo Local:
echo %LOCAL_IP%:%LOCAL_PORT%
echo.
echo Remote:
echo %TARGET_IP%:%TARGET_PORT%
echo.
echo ============================================================
echo.

echo Connecting...
echo.

ssh -i "%SSH_KEY%" -N ^
  -L %LOCAL_IP%:%LOCAL_PORT%:%TARGET_IP%:%TARGET_PORT% ^
  -p 22 ^
  "!SESSION_ID!@%BASTION_HOST%"

REM ============================================================
REM SSH CLOSED / FAILED
REM ============================================================

echo.
echo ============================================================
echo                 SSH CONNECTION CLOSED
echo ============================================================
echo.

echo Retrying in 10 seconds...
echo.

timeout /t 10 /nobreak >nul

goto SSH_RETRY