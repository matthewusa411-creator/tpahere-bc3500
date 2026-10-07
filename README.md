# Shulker Dupe — Fabric 26.2

Makes a shulker box broken by a non-creative player drop an extra copy of the shulker box.

Because the mod duplicates the vanilla shulker drop, the copied shulker keeps the same stored contents and item components.

## Requirements

- Minecraft Java Edition 26.2
- Fabric Loader 0.19.3 or newer
- Fabric API 0.161.0+26.2 or newer
- Java 25

## Build

Run from the project folder:

```text
./gradlew build
```

On Windows:

```text
gradlew.bat build
```

The finished mod jar will be in `build/libs/`.

## Install

Put the built jar in your Minecraft `mods` folder along with Fabric API.

## Behavior

Survival/Adventure:

`filled shulker -> break -> 2 identical filled shulkers`

Creative:

No extra drop is added.
