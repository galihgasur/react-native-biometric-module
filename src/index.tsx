import { NativeModules } from 'react-native';

type BiometricModuleType = {
  multiply(a: number, b: number): Promise<number>;
  canEvaluatePolicy(callback: Function): void;
  biometricType(callback: Function): void;
};

const { BiometricModule } = NativeModules;

export default BiometricModule as BiometricModuleType;
