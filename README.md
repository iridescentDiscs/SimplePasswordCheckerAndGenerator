# Password Strength Checker and Generator
This is a simple Java Swing applet that can check your password's strength and generate new ones according to your desired length and whether you would like to include special characters. This was written for the University of Utah's CS 3090 Ethics in Computing course and adheres to the guidelines outlined for the assignment this corresponds to.

## Running This Program
Included in this repo is a compiled .jar file that can be run by executing the following command in a terminal of your choosing: `java -jar <path_to_jar_on_your_machine>`

Should you need to recompile this jar file yourself, you will need Java 17 or later installed.

## Limitations
Given the nature of this applet, only simple checks were made to verify the strength of a user's entered password. In particular, users are encouraged to avoid duplicate characters and no additional check is made to see whether duplicate characters repeat in sequence or simply appear multiple times.

The collection of special characters used to generate passwords is also preset and cannot be directly modified while the app is running, which may hinder users who cannot use certain characters included in the original data set.

## Ethical Considerations
This applet is run entirely locally and be run offline should a user choose to do so. This enables users to verify password strength and generate new ones without any data being transmitted to other sources from the applet. On the strength checker tab, users' passwords are obscured to provide a greater level of privacy, but the same is not true of the generator. This was done so that copying generated passwords was easier to do.