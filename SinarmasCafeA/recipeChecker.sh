Exist () {
    local MENULIST=("PINEAPPLE PIE" "APPLE PIE" "FRUIT PARFAIT");
    for u in "${MENULIST[@]}"; do if [ "$u" == "$1" ]; then return 1; break; fi; done;
    return 0
}

Available () {
    if [ "$1" == "PINEAPPLE PIE" ]; then
        CheckPineapplePie "$1"
    elif [ "$1" == "APPLE PIE" ]; then
        CheckApplePie "$1"
    else 
        CheckFruitParfait "$1"
    fi
}

CheckPineapplePie () {
    pineappleAvail=0
    for u in "${FRUITLISTARR[@]}"; do if [ $u == "pineapple" ]; then pineappleAvail=`expr $pineappleAvail + 1`; fi; done;
    if [ $pineappleAvail -ge 3 ]; then echo "You shall have $1"; else echo "You shall not have $1"; fi
}
CheckApplePie () {
    appleAvail=0
    for u in "${FRUITLISTARR[@]}"; do if [ $u == "apple" ]; then appleAvail=`expr $appleAvail + 1`; fi; done;
    if [ $appleAvail -ge 3 ]; then echo "You shall have $1"; else echo "You shall not have $1"; fi
}
CheckFruitParfait () {
    appleAvail=0
    pineappleAvail=0
    for u in "${FRUITLISTARR[@]}"; do if [ $u == "apple" ]; then appleAvail=`expr $appleAvail + 1`; fi; done;
    for u in "${FRUITLISTARR[@]}"; do if [ $u == "pineapple" ]; then pineappleAvail=`expr $pineappleAvail + 1`; fi; done;
    if [[ ($appleAvail -ge 2) && ($pineappleAvail -ge 2) ]]; then echo "You shall have $1"; else echo "You shall not have $1"; fi
}

filename=$1
n=1
declare -a FRUITLISTARR
while read line; do
FRUITLISTARR[n]=$(echo "$line" | tr '[:upper:]' '[:lower:]'| tr ',' ' ')
n=$((n+1))
done < $filename
# echo "${FRUITLISTARR[@]}"

selected=$2
Exist "$selected"
exist=$?

if [ $exist == 1 ]; then
    Available "$selected"
else
    echo "We do not have that on the menu"
fi