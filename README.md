# OS-Single-And-MultiThreadedServer


- To start the application run the main method in the Main class.
- The application has a simple interface where you can type "m" to start the multi-threaded server or "s" to start a single-threaded one.
- Any other input will close the application.
- The chosen server will only run for 9 seconds before stopping.
- To see the HTML output you can open localhost:8080 in a browser window.

![image](https://user-images.githubusercontent.com/97608891/218797117-ffe6f885-012e-4375-bf48-cdfe85d1887c.png)

We observed that the the single thread server would only process one request at the time. When we used multiple threads the application would start processing a new request every time we would refresh the website.
