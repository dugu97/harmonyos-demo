import app from '@system.app';
export default {
    onCreate() {
        console.info('AceApplication onCreate');
    },
    onDestroy() {
        console.info('AceApplication onDestroy');
    },
    onBackPressed(){
        console.info('AceApplication onDestroy1');
        app.terminate();
    }
};
