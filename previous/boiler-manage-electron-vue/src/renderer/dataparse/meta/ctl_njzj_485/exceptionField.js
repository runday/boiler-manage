import parentExceptionField from '../exceptionField'
export default class exceptionField  extends parentExceptionField {
  haveValue(...bytes){
    this.value =  bytes[1] & 0xFF | (bytes[0] & 0xFF) << 8 ;
    return 0x7FFF != value;
  }
}
