# EventTrackingApp_Kwayisi

## Summary
EventTrackingApp_Kwayisi is an app that encourages users to store information regarding upcoming events they are interested in. Saved apps will
be stored in a user's account and can be edited or deleted from their event dashboard. Users will receive SMS and notifications regarding saved event
a day prior to the event occuring. Offering an intuitive and user-centric experience for all.

## How did users influence design choices?
In building this app, the users needed to have a screen that would enable sign-in/login, a screen to enter event information and view saved events, and 
a screen to edit an event. The login screen only contains the title of the app, 2 textView tags (one for username and one for password), and 2 buttons (
a login and ceeare account button). While the add  event screen also contains 2 textView tags for event name and date with a button to confirm the users input.
In addition, below is the dashboard presenting all the event information with an edit and delete button to the far right. Lastly, the edit event screen uses a similar
layout to that of the add event screen, but without the dashboard at the bottom of the screen.
(Looking back, my designs were successful because they were simplistic. Initially, I wanted to add more to 
the UI design, but due to time constraints I stuck to a simplified design and layout. Though, this worked to my advantage because it led to an easy to 
navigate UI for the users to follow alongside a consistent color scheme. Keeping in mind that users want organized and easy to navigate apps.

## Approach to developing my app
Before starting this project, I decided I wanted to create the layout of each screen first. That way I'd have a better understanding of what elements needed
to accommodate for user interactivity. For instance, upon creating the login screen, I knew the user needed to be able to enter characters into the textView
based on being aware that I was included in the screen's layout. This enabled me to then look at some documentation on Android Studio's official documentation site
about how to develop code for the textView in Java. After reviewing the code, I would try to write my own code to accomplish this task. In the future, I could apply 
the strategy of applying a simple UI app layout and reading documentation because it helps give me a visual idea of what needs to be functional and documentation
helps improve my understanding of different code functions and implementations. 

## Conducting Tests For Functionality
I tested my app in two ways, running the Android emulator and unit testing. Running the Android emulator was integral to the testing process because it allowed 
me to run and test the app in real-time. This is important because it allows me to test the app from the perspective of a user rather than a developer. Where I can
test not only the functionality, but also understand how the user will see the UI as well. While unit testing was great for testing different functions.
The inclusion of unit testing helped produce cleaner and maintainable code by detecting bugs early and demonstrates how a unit of code is supposed to function. 
The process of testing is important in the tech industry because it allows developers to ensure that their program is operating correctly before being published to
the public. Preventing potential losses in finance and reception from potential errors.

## Challenges
My biggest challenge during development was that I initially wanted the event adding screen to just be a space to add events and not have the dashboard included. 
However, due to time constraints, I decided I should combine the two into one screen. The main challenge with this innovation was understanding where it be 
appropriate to place the dashboard relative to the event textView. I eventually placed it at the bottom because if I'm the user, I feel as though that knowing
The ability to enter information is slightly more important than viewing the event on the dashboard. 

## EventTrackingApp_Kwayisi MVP
The MVP of EventTrackingApp_Kwayisi was providing multi-user Android event management app in Java leveraging Room (SQLite) with enforced foreign key 
constraints and persistent local storage. This was vital to not only my skills and knowledge in computer science, but it also allowed my app to mature.
The integration of this feature enabled the use of DAO operations and SQLite implementation to produce a consistent and maintainable  system that values
user security and integrity. 
