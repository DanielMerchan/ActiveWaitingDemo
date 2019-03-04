# ActiveWaitingDemo
Demonstration of an Oracle ADF Application making an active waiting over an asynchronous Oracle BPM Process.
The ADF Application simulates a typical BPM Task List / Worklist and contains a button for starting in the background a **BPM Asyncrhonous Process**. This is very typical scenario by using JMS Queues.

In this Demonstration, the application sniffers what is happening in the background of the BPM Process and redirects the user to the first Human Task screen (when reached) instead of coming back to the Task List.

The Popup displaying the Active Waiting displays the BPM Audit Image and a Text Area of the name of the activities being executed.

Image Placeholder

# Explanation of the Demonstration

Introduce Blog Link

## Applications / Project Structure

### ActiveWaiting
Contains:
- **ActiveWaitingDB**: Necessary Schema - Table DDL scripts required by the Demonstration.
- **ProcessInfoClient**: Java Proxy JAX-WS for the WebService deployed in **ActiveWaitingSB** for interacting with the Demo Table / Schema
- **DemoProcessClient**: Java Proxy JAX-WS for the WebService deployd as part of **ActiveWaitingBPM** process.
- **CustomTaskList**: Main ADF View Controller Application which simulates the Task List and has the buttons for testing the demo.

### ActiveWaitingSB
It is an Oracle Service Bus Applicaction with the **DemoSB** project which exposes the operations with CUSTOM_PROCESS_INFO table of the Demo

### ActiveWaitingBPM
Contains the BPM Process with an Asynchronous Interface.

## Installation
The next steps are suppose you have experience with Oracle Database, Oracle Service Bus and Configuring Data Sources in Oracle WebLogic Application Server

**Installing the DB Schema**
- Open **ActiveWaiting** Application / **ActiveWaitingDB** project.
- Use the scripts for creating (if needed) a schema and then the table required by the demo

**Create the Data Source and DbAdapter**
The **ActiveWaitingSB / DemoSB** Service Bus Project requires the eis/DB/Demo created in the *DbAdapter* which points to the Data Source *jdbc/DemoDS* created for connecting to the Schema containing our Custom Table.

**Deploy and Test trhe ActiveWaitingSB**:
Deploy the **DemoSB** Service Bus project and check it works. You can add dummy data in the CUSTOM_PROCESS_INFO to check it.

**Deploy the ActiveWaitingBPM**
Deploy the **ActiveWaitingBPM** Application including the BPM Process and the UI of the Human Task.
You can test the BPM Process with Oracle Enterprise Manager

**Run / Deploy the ActiveWaiting - Custom Task List**
Deploy the **CustomTaskList** Application (or just run the **welcome.jsf** page). In this application you can click *Start Async Process* which will open an ADF Popup showing how the BPM Process is being executed on behind.
