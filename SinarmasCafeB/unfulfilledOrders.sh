filename=$1
menu=$(echo "$2" | tr '[:upper:]' '[:lower:]')
date=$3

# read file data and create and array
n=1
declare -a ORDERLIST
while read rawLine; do
line=$(echo "$rawLine" | tr '[:upper:]' '[:lower:]')
if [[ ("$line" =~ ^"$date") && ("$line" =~ "fulfilled=false")  && ("$line" =~ "$menu")]]; then
ORDERLIST[$n]=$rawLine
n=$((n+1))
fi
done < $filename

ORDERLENGTH=${#ORDERLIST[@]}

# sort the data from latest
declare -a ORDERLISTSORTED

for u in "${!ORDERLIST[@]}"; do
    # ORDERLISTSORTED[$n]=$u
    tanggalRaw=$(echo "${ORDERLIST[$u]}" | grep -E "(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2})" --only-matching)
    tanggal=$(date -j -f '%Y-%m-%d %H:%M:%S' "$tanggalRaw" +%s)

    n=1
    for u1 in "${ORDERLIST[@]:u}"; do
        tanggalRaw2=$(echo "${u1}" | grep -E "(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2})" --only-matching)
        tanggal2=$(date -j -f '%Y-%m-%d %H:%M:%S' "$tanggalRaw2" +%s)
        if [ "$tanggal" -lt "$tanggal2" ]; then 
            x="${ORDERLIST[$u]}"
            ORDERLIST[$u]=$u1
            ORDERLIST[$n]=$x
        fi
        n=$((n+1))
    done
done 

ORDERLISTNEW=("${ORDERLIST[@]:1:3}")

for ((i=${#ORDERLISTNEW[@]}; i>=0; i--))
do
    echo ${ORDERLISTNEW[$i]};
done



# echo "${ORDERLISTNEW[@]}"