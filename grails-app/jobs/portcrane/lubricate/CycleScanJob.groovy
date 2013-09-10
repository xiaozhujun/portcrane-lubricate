package portcrane.lubricate

import dbmodel.lubricate
import db.insertToDb;
import java.util.*;


class CycleScanJob {
    static triggers = {
     /* simple repeatInterval: 5000l // execute job once in 5 seconds*/
        simple repeatInterval: 100000
    }

    def execute() {
        // execute job
     print "job run";
    /* new insertToDb().executeJob();     实现自动检测润滑周期插入sms-send数据库*/
      /* new insertToDb().executeJobByHand();*/
    }
}
