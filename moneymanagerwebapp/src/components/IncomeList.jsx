import { Download, LoaderCircle, Mail } from "lucide-react"
import TransactionInfoCard from "./TransactionInfoCard";
import moment from "moment";
import '../index.css';
import { useState } from "react";

const IncomeList = ({ transactions, onDelete, onDownload, onEmail }) => {
    const [loading, setLoading] = useState(false);

    const handleEmail = async () => {
        setLoading(true);
        try {
            await onEmail();
        } finally {
            setLoading(false);
        }
    }

    const handleDownload = async () => {
        setLoading(true);
        try {
            await onDownload();
        } finally {
            setLoading(false);
        }
    }
    return (
        <div className="card ">
            <div className="flex items-center justify-between ">
                <h5 className="text-lg">Income Sources</h5>
                <div className="flex items-center justify-end gap-2 ">
                    <button disabled={loading} className="card-btn bg-purple-100 text-purple-700 border border-purple-400 px-4 py-0.5 rounded-lg hover:bg-purple-300 transition-all shadow-md hover:shadow-lg" onClick={handleEmail}>
                        {loading ? (
                            <>
                                <LoaderCircle className="w-4 h-4 animate-spin" />
                                Emailing...
                            </>
                        ) : (
                            <>
                                <Mail size={15} className="text-base" />Email
                            </>
                        )}
                    </button>
                    <button disabled={loading} className="card-btn bg-purple-100 text-purple-700 border border-purple-400 px-4 py-0.5 rounded-lg hover:bg-purple-300 transition-all shadow-md hover:shadow-md " onClick={handleDownload}>

                        {loading ? (
                            <>
                                <LoaderCircle className="w-4 h-4 animate-spin" />
                                Downloading...
                            </>
                        ) : (
                            <>
                                <Download size={15} className="text-base" />Download
                            </>
                        )}
                    </button>
                </div>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2">
                {/** Display the incomes */}
                {transactions?.map((income) => (
                    <TransactionInfoCard
                        key={income.id}
                        title={income.name}
                        icon={income.icon}
                        date={moment(income.date).format('DD MMM YYYY')}
                        amount={income.amount}
                        type="income"
                        onDelete={() => onDelete(income.id)}
                    />
                ))}
            </div>
        </div>
    )
}

export default IncomeList;