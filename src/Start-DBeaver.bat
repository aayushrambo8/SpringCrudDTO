@echo off
title OCI Bastion - Create MySQL Session

echo.
echo ==========================================
echo       OCI BASTION MYSQL SESSION
echo ==========================================
echo.

set "BASTION_ID=YOUR_BASTION_OCID"
set "TARGET_IP=10.0.1.57"
set "TARGET_PORT=3306"
set "SSH_PUBLIC_KEY=%USERPROFILE%\root.pub"

echo Creating Bastion session...
echo.

oci bastion session create-port-forwarding ^
  --bastion-id "%BASTION_ID%" ^
  --target-private-ip "%TARGET_IP%" ^
  --target-port %TARGET_PORT% ^
  --ssh-public-key-file "%SSH_PUBLIC_KEY%" ^
  --session-ttl 10800

echo.
echo Session creation request sent.
echo The session will become ACTIVE shortly.
echo.

pause