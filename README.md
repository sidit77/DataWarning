# DataWarning

## Overview
This app displays a warning if you switch to a blacklisted app (e.g. YouTube) and you're currently connected to a mobile/metered connection.

I created this app because I got frustrated when I yet again used up over half my monthly data volume just because i forgot to turn on WiFi before whatching YouTube.

To my knowledge this app uses pretty much no battery life or performance even if its running 24/7.

## Which devices are supported?
I honestly don't know. I created this app mainly for myself and i had/have no experience in android app development so i can only say that it work on my phone (Nokia 7 plus) but i don't see why it shouldn't work on other phones.

## How does it work?
After installing go the Settings -> Accessibility and enable the app. There you can also set for which apps this app should be active.

## Why is this an accessibility service?
Because an accessibility service was the best way i found to monitor which app is currently displayed. It does nothing but compare the name of the current app to the blacklist. If you don't trust me you can look at the entire source code (14 lines) [here!](https://github.com/sidit77/DataWarning/blob/master/app/src/main/java/com/github/sidit77/datawarning/AppOpenService.java) 

## Screenshots
![Screenshots](https://i.ibb.co/47bqyjp/unnamedc.png)

## Downloads
[Here](https://github.com/sidit77/DataWarning/releases)

## License

MIT License
