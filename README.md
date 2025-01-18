
# Expo Selectable Text

ExpoSelectableText is a custom React Native component that provides text selection functionality.

> ⚠️ Note: This component is currently supported only on Android.

For iOS, similar functionality can be achieved using a TextInput component combined with the `onSelectionChange` and `onTouchEnd` props. However, a dedicated iOS implementation written in Swift is part of the planned future development.

# Installating the library
  
```
npm install expo-selectable-text
```
    

#### Android

<img src="https://github.com/user-attachments/assets/c81c0621-dbea-4fd5-ae06-e35d4df76830" alt="Android Screenshot" style="width: 400px; height: auto;" />


#### Examples

```tsx
import { ExpoSelectableTextView } from "expo-selectable-text";

<ExpoSelectableTextView
        style={{ flex:1 }}
        onSelectionEnd={(event) => alert(JSON.stringify(event.nativeEvent))}
        fontSize={18}
        text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa."
/>
```

Using FlatList:

```tsx
<FlatList
        data={[1, 2, 3, 4, 6]}
        // You need to use this prop to avoid clipping the text
        removeClippedSubviews={false}
        renderItem={() => (
          <ExpoSelectableTextView
            style={{ height: 400 }}
            onSelectionEnd={(event) => alert(JSON.stringify(event.nativeEvent))}
            fontSize={18}
            text="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa."
          />
        )}
      />
```   

To use a custom font family, you must configure it with Expo Fonts.

```
npx expo install expo-font
```

```
{
  "expo": {
    "plugins": [
      [
        "expo-font",
        {
          "fonts": ["./assets/fonts/Inter-Black.otf"]
        }
      ]
    ]
  }
}
```

```
  <ExpoSelectableText
  ...
  fontFamily={"Inter-Black"}
  />
```



# Contributing

Contributions are very welcome! 
