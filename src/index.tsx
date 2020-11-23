import { NativeModules } from 'react-native';

type BiometricModuleType = {
  multiply(a: number, b: number): Promise<number>;
};

const { BiometricModule } = NativeModules;

export default BiometricModule as BiometricModuleType;
