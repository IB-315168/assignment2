# assignment2
SDJ3 Assignment 2
## Import instructions
1. Clone into project, verify that all files are present
2. Open right-hand side Maven window (same sidebar you find "Notifications" and "Database" on)
3. From "Lifecycle", select first "clean" and wait for the process to finish
4. Verify that process has finished (i.e. orange directory "target" in Project Explorer is no longer present)
5. From "Lifecycle", select now "install" and wait for the process to finish
6. Verify that process has finished ("target" directory should be present now) and outcome has been a success (Run window should contain "build success" message)
7. Go to com.sep3yg9.assignment2.gprc and open either of the classes

If you open the class and "extends" part is not highlighted red, you're ready to go.
Otherwise:
1. Go to "target" directory -> generated-sources and find directory "protobuf"
2. If it is orange - select the "protobuf" directory, right-click on it and select option "Mark Directory as" -> "Generated Sources root"

If any other problems occur, contact me directly.
## Description
Implementation of slaughter house in form of RESTful web service.
## Requirements - Part 2
1. Service has to store data about animals:
    - registration number
    - date
    - weight
    - origin
2. Service should provide possibility of reading specific animals' details.
3. Service should provide possibility of searching animals by:
    - date
    - origin
