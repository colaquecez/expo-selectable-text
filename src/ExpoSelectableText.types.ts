import type { StyleProp, ViewStyle } from "react-native";

export type OnLoadEventPayload = {
  text: string;
  selectionStart: number;
  selectionEnd: number;
};

export type ExpoSelectableTextViewProps = {
  text: string;
  onSelectionEnd: (event: { nativeEvent: OnLoadEventPayload }) => void;
  fontSize?: number;
  style?: StyleProp<ViewStyle>;
};
