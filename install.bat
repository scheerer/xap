@echo off

copy javajre.exe c:\javajre.exe
copy xap.jar c:\xap.jar
copy Readme.txt c:\ReadmeX.txt

c:
cd ..
cd ..
cd ..
cd ..
cd ..
mkdir Xap
copy javajre.exe c:\Xap\javajre.exe
copy xap.jar c:\Xap\xap.jar
copy ReadmeX.txt c:\Xap\Readme.txt

del javajre.exe
del xap.jar
del ReadmeX.txt

cd Xap
javajre.exe

echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo.
echo Installation complete.
echo You can run Xap or Quit.
echo.
echo Press Ctrl + C to Quit or
pause
@echo off
java -jar xap.jar
