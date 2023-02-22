# ModbusPal Enhanced v1.7 by @mrhenrike
ModbusPal - a Java MODBUS simulator
- Forked by https://sourceforge.net/projects/modbuspal/
- Changed by André Henrique (Twitter @mrhenrike / LinkedIn @mrhenrike)

This release of ModbusPal Enhanced v1.7 is a release candidate version.

### About ModbusPal
ModbusPal is a MODBUS slave simulator. Its purpose is to offer an easy to use interface with the capabilities to reproduce complex and realistic MODBUS environments. The core of ModbusPal is written in Java. TCP/IP is supported natively, and the serial communication is supported if RxTx library is installed on the computer.

### What's new in v1.7?
- Fixing to load a xmpp file
- Fixing automation chart (requires jfreechart)
- Fixing add new scripts
- Added scripts samples on "Examples" directory
- Updated the User Guide
- Added optional command line arguments for the executable JAR:
    --loadFile - Loads a previously saved file at launch. Provide the absolute path.
    --portNumber - An IP port number to connect this current configuration.
    --help - A command line help message to display the command line arguments.
    --hide - Start ModbusPal without showing the UI elements so it can run in a headless environment.
- ModbusPal can be executed without a GUI
- Updated to jython 2.7.0
- Updated to jfree-freechart 1.0.15 
- Updated to jfree-jcommon 1.0.17 
- Updated to xml-apis 1.3.04
- Updated to itext 2.1.5 
- Updated to rxtx 2.2.2
- Generated a JavaDoc dist/javadoc
- Added a docker builder

### Known limitations:
- "Tuning" features are not documented yet
- Javadoc very incomplete

### How to run
You can add your xmpp project from modbuspal as first argument:
`java -jar /path/absolute/modbuspal-runtime.jar -loadFile=/path/absolute/samples/project01.xmpp`

The load file function is working only xmpp project in 1.7 version and always use absolute path when you informe a file

or you can set MODBUSPAL_PROJECT environment variable as path to your project file and then just run
`java -jar .\target\modbuspal-runtime.jar`

### Run without UI ###

You can start ModbusPal without showing the UI elements so it can run in a headless environment, or if you just don't care to open the window with the -hide flag. You can use it in conjuction with a project file to make modbuspal start all automations and start listening for requests using the command:
```
java -jar <<pathtojar>>/ModbusPal.jar -loadFile=<<projectfile>> -hide
```
Only works with TCP/IP over port 502 currently. You also need to have all your slaves listening on localhost.

## How to build (Using Docker) ##
--------------------------------------
```docker build -t modbuspal-builder .
docker run --name modbuspal-builder modbuspal-builder
docker cp modbuspal-builder:/usr/src/app/dist/ModbusPal.jar .
docker cp modbuspal-builder:/usr/src/app/dist/modbuspal-help.zip .
docker cp modbuspal-builder:/usr/src/app/dist/modbuspal-javadoc.zip .
```

__You can either run with a included sample project__
`docker run -p502:502 -e MODBUSPAL_PROJECT=/projects/project01.xmpp modbuspalruntime`

__Or mount your project and refer to it via environment variables__
`docker run -p502:502 -v ${path-to-my-projects}:/projects -e MODBUSPAL_PROJECT=/projects/my-project01.xmpp modbuspalruntime`

## Source-code Project in Java
### Prerequisites ###
- Apache Netbeans (https://netbeans.apache.org/)
- JDK 17 (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Download ivy http://ant.apache.org/ivy/download.cgi (suggest extracting to C:\jars)

### Setup Project ###
1. Download and install netbeans https://netbeans.apache.org/download/index.html
1. Open Netbeans and select `tools > options > Java > ant` and add the ivy jar to the classpath
1. Run project

### Build Jar ###
1. Build Project (click hammer or F11)
1. Jar will be in [ProjectFolder]/dist

