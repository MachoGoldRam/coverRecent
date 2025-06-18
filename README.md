# A module to cover app previews from the recent task / app overview list using a customizable overlay bitmap image.
Designed in pure Kotlin, Jetpack Compose & Material Design 3. Can be used as a template for any [LSposed Refactored](https://github.com/JingMatrix/LSPosed) module with an application selection list.

## Reason for existing?
The default AOSP doesn't provide such a key privacy feature as this one, so I decided to try my shot at it.

## Usage
> Tested on Android 14 (/e/OS by Murena); It will likely work on android 10 through 13.
1. Select `System framework` (package name may be `android` or `system` or empty, [see this](https://github.com/LSPosed/LSPosed/releases/tag/v1.9.1)) in module scope, and activate this module.
2. Force stop this module/app from the android system settings.
3. Select the apps you want to cover the previews of from the recent app list in the module settings (if app list is not shown, you can manually import/export settings to edit config).
4. Set the desired cover overlay to `/sdcard/.private-overlay.bmp` (`/scard` Is the user folder root where Downloads, Pictures, Music, etc... - Are saved).
5. Reboot whenever you modify the list, else changes won't be visible until the next system startup.

## Scope
- Elevated/Rooted Android

## Project URL
Home URL: <https://github.com/MachoGoldRam/coverRecent>

## HELP! IT'S NOT WORKING!
Open an issue, providing your Android version, `/system/framework/framework.jar` and all `/system/framework/framework{INT}.jar` if they exist.
Code refactoring contributions are also appreciated.

## References
<https://stackoverflow.com/questions/57266451/get-list-of-apps-of-all-users>
<https://github.com/LibChecker/LibChecker/pull/821/files> (Apache-2.0 license)
[rootAVD](https://gitlab.com/newbit/rootAVD)

## Technical Details
UI: Material Design 3 + Jetpack Compose + Kotlin.
Hook: Hook `com.android.server.wm.RecentTasks.isVisibleRecentTask(com.android.server.wm.Task)`, `(callMethod(param.args[0], "getBaseIntent") as Intent).component?.packageName` is package name.

## License
Apache-2.0 License or MIT License are all OK.
