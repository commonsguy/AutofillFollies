This repository contains code and instructions for reproducing a security
flaw with Android 8.0's autofill implementation. Simply put: an improperly-written
autofill service can leak data to malicious activities. A longer explanation
of what is going on can be found in
[the associated white paper](https://github.com/commonsguy/AutofillFollies/blob/master/WHITE_PAPER.md).

This repository contains two separate code bases:

- `android-AutofillFramework/` is a copy of
[Google's official autofill service sample implementation](https://github.com/googlesamples/android-AutofillFramework),
as of 30 July 2017. This is an improperly-written autofill service, though
future updates to the official sample may correct this. Note that the
copy of Google's code hosted in this repository has been slightly modified:

    - It drops back to Gradle 3.3 and the 2.3.3 Android Plugin for Gradle, so
    the sample can be built using Android Studio 2.3.3

    - It upgrades the Support Library version to `26.0.0` from the beta used
    by the official sample

- `AFClient/` is a sample app demonstrating how malicious activities can hide
widgets from users yet still collect autofill data from the autofill service.

## Reproducing the Flaw

These steps will reproduce the problem on an Android device running O Developer Preview 4.

1. Build and install the autofill service found in `android-AutofillFramework/Application`
onto the test device

2. Go into Settings > System > Languages & input > Advanced > Autofill service and
choose the "Multi-Dataset Autofill Service" implementation, which comes from the
sample app from the previous step

3. Build and install the `AFClient` sample app, which will give you an `AFClient`
launcher icon

4. Tap that launcher icon, which brings up an activity with a username and
password field

5. Type whatever value you like for the username and `password` for the password, then
click "Save", which takes you to an activity that shows you the username and
password that you entered

6. Press BACK twice, exiting the app

7. In the bottom sheet dialog that appears, opt to save the data in the autofill
service

8. Tap that launcher icon again

9. In the original form, once the username field gets the focus, in the drop-down
list of autofill candidates, tap "dataset-0", and confirm that both the username
and the password fields are filled in

10. Click Save, and confirm that the results show the same username and password
that you had entered originally

11. Press BACK, and from the overflow menu in the action bar, choose a scenario:

    - Invisible: the password field is marked as `invisible`
    - Tiny: the password field has a size of 1dp x 1dp
    - Zero-Size: the password field has a size of 0dp x 0dp
    - Off-Screen: the password field is 1000dp below the username field, which should push it off-screen on most devices
    - Behind: the password field is behind a white box on the Z axis

12. In the form that appears, choose "dataset-0" from the autofill drop-down,
then click Save, and see that the result activity shows both the username
and the password... even though you as a user did not see a password field
get filled in

13. Repeat steps 11 and 12 for the other scenarios, as desired

If you encounter problems with the demonstration, file an issue in this project. 

## Licenses

Both projects are licensed under the Apache Software License 2.0.

Google's autofill service implementation is Copyright &copy; 2017 The Android Open Source Project, Inc.

The `AFClient` sample app is Copyright &copy; 2017 CommonsWare, LLC.
