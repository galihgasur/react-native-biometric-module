import * as React from 'react';
import { StyleSheet, View, Text, Button } from 'react-native';
import BiometricModule from 'react-native-biometric-module';

export default function App() {
  const [canEvaluate, setCanEvaluate] = React.useState<boolean | undefined>();
  const [errorEvaluate, setErrorState] = React.useState<string | undefined>();
  const [bioType, setBiometricType] = React.useState<string | undefined>();
  const [errorType, setErrorType] = React.useState<string | undefined>();

  const canEvaluatePolicy = () => {
    BiometricModule.canEvaluatePolicy(
      (canEvaluate: boolean, errorEvaluate: string) => {
        setCanEvaluate(canEvaluate);
        setErrorState(errorEvaluate);
      }
    );
  };

  const biometricType = () => {
    BiometricModule.biometricType(
      (biometricType: string, errorType: string) => {
        if (biometricType) {
          setBiometricType(biometricType);
        } else {
          setErrorType(errorType);
        }
      }
    );
  };

  return (
    <View style={styles.container}>
      <Button onPress={canEvaluatePolicy} title="Evaluate Policy" />
      <Text>
        {canEvaluate === true ? 'The policy can be evaluated.' : errorEvaluate}
      </Text>
      <Button onPress={biometricType} title="Biometric Type" />
      <Text>{bioType ? bioType : errorType}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
