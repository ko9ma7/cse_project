from matplotlib import pyplot as plt

# Enter capacity(kW) and daily pattern data of WT, PV, Load
Pwt = [
    (0.81 * 5), (1.00 * 5), (0.57 * 5), (0.38 * 5), (0.22 * 5), (0.35 * 5), (0.44 * 5), (0.36 * 5),
    (0.57 * 5), (0.82 * 5), (0.58 * 5), (0.33 * 5), (0.35 * 5), (0.64 * 5), (0.64 * 5), (0.22 * 5),
    (0.18 * 5), (0.10 * 5), (0.05 * 5), (0.08 * 5), (0.15 * 5), (0.08 * 5), (0.14 * 5), (0.13 * 5)
]
Ppv = [
    (0.0 * 10), (0.0 * 10), (0.0 * 10), (0.0 * 10), (0.0 * 10), (0.0 * 10), (0.0 * 10), (0.0 * 10),
    (0.2 * 10), (0.5 * 10), (0.7 * 10), (0.9 * 10), (1.0 * 10), (0.9 * 10), (0.8 * 10), (0.7 * 10),
    (0.53125 * 10), (0.2 * 10), (0.0 * 10), (0.0 * 10), (0.0 * 10), (0.0 * 10), (0.0 * 10), (0.0 * 10)
]
Pload = [
    (0.33 * 10), (0.32 * 10), (0.32 * 10), (0.31 * 10), (0.30 * 10), (0.30 * 10), (0.34 * 10), (0.40 * 10),
    (0.50 * 10), (0.70 * 10), (0.69 * 10), (0.60 * 10), (0.55 * 10), (0.70 * 10), (0.68 * 10), (0.60 * 10),
    (0.53 * 10), (0.50 * 10), (0.47 * 10), (0.45 * 10), (0.40 * 10), (0.36 * 10), (0.34 * 10), (0.33 * 10)
]

# Enter efficiency of inverter
Ef_inv = 0.95

# Enter constraints of LOLP and dummy loads
Con_LOLP = 0.05
Con_dummy = 0.04

# Enter battery data, initial SOC(%), SOC_min(%), SOC_max(%), P_bc(kW), P_bd(kW)
C_bat = 20				# Capacity(kWh)
SOC_ini = 30			# initial SOC of battery
SOC_min = 10			# minimum SOC of battery
SOC_max = 90			# maximum SOC of battery
P_bc = [0] * 24		    # output of battery in charge mode
P_bd = [0] * 24		    # output of battery in discharge mode
Ef_bc = 0.9			    # efficiency of battery in charge mode
Ef_bd = 0.85			# efficiency of battery in discharge mode
r_sd = 0.002			# self - discharge rate of battery

# Enter dummy load capacity(kW)
P_dummy = [0] * 24
E_dummy = 0

# Enter diesel generator capacity(kW), output(kW) and efficiency
P_dgr = 10
P_dg = [0] * 24
Ef_dg = 0.95

# NPSP algorithm
t = 1							# initial time
LOLP = [0] * 24					# initial LOLP at each time
LOLP_sum = 0
E_bat = (C_bat * SOC_ini) / 100	# initial kWh capacity of battery at each time
SOC = [0] * 24					# initial SOC of battery at each time

# iteration for day calculation(24h)
for t in range(24):
    SOC[t] = E_bat / C_bat * 100		# SOC calculation of battery at each time

    # occurred surplus energy in HRES system
    if Pwt[t] > Pload[t]:
        # check SOC of battery whether the SOC has enough
        if SOC[t] < SOC_max:
            P_bc[t] = (Pwt[t] - Pload[t] * Ef_inv + Ppv[t]) * Ef_bc		    # calculate charge capacity of battery
            E_bat = E_bat * (1 - r_sd) + P_bc[t] * Ef_bc				    #  accumulate capacity of battery
        else:
            P_bc[t] = 0													    # calculate charge capacity of battery
            P_dummy[t] = (Pwt[t] - Pload[t]) + Ppv[t] * Ef_inv			    # calculate capacity of dummy loads to cover surplus energy in HRES system
            E_dummy = E_dummy + P_dummy[t]								    # accumulate capacity of dummy loads to check total used capacity of dummy loads

    # occurred surplus energy in HRES system
    if Pwt[t] <= Pload[t] and (Pwt[t] + Ppv[t] * Ef_inv) > Pload[t]:
        # check SOC of battery whether the SOC has enough
        if SOC[t] < SOC_max:
            P_bc[t] = (Pwt[t] + Ppv[t] * Ef_inv - Pload[t]) * Ef_bc*Ef_inv  # calculate charge capacity of battery
            E_bat = E_bat * (1 - r_sd) + P_bc[t] * Ef_bc					# accumulate capacity of battery
        else:
            P_bc[t] = 0													    # calculate charge capacity of battery
            P_dummy[t] = Pwt[t] + Ppv[t]*Ef_inv - Pload[t]					# calculate capacity of dummy loads to cover surplus energy in HRES system
            E_dummy = E_dummy + P_dummy[t]									# accumulate capacity of dummy loads to check total used capacity of dummy loads

    # occurred deficit energy in HRES system
    if Pwt[t] <= Pload[t] and (Pwt[t] + Ppv[t]*Ef_inv) <= Pload[t]:
        # check SOC of battery whether the SOC has lack of capacity
        if SOC[t] > SOC_min:
            P_bd[t] = (Ppv[t] - (Pload[t] - Pwt[t]) / Ef_inv) * Ef_bd		# calculate discharge capacity of battery
            E_bat = E_bat * (1 - r_sd) - abs(P_bd[t]) * Ef_bd				# accumulate capacity of battery
        else:
            P_bd[t] = 0													    # calculate charge capacity of battery
            P_dg[t] = (Pload[t] - Pwt[t] - Ppv[t] * Ef_inv) / Ef_dg		    # calculate capacity of diesel generator to cover deficit energy in HRES system
            # check the capacity of diesel generator whether it exceeds rated capacity of diesel generator
            if P_dg[t] > P_dgr:
                LOLP[t] = 1					    # evaluate LOLP at time t
                LOLP_sum = LOLP_sum + LOLP[t]   # accumulate LOLP at each time
            else:
                LOLP[t] = 0				        # evaluate LOLP at time t

    # check capacity of HRES with constraints of LOLP and dummy loads
    if E_dummy <= 0: print("Need to increasing capacity of PV or WT.\n")
    else:
        if E_dummy > max(Pload): print("Need to decreasing capacity of PV or WT.\n")
        else:
            if LOLP_sum / t > Con_LOLP: print("Need to increasing capacity of PV or WT.\n")
            else: print("Present capacities of PV and WT are optimal.\n")

# Pwt 그래프
plt.subplot(3, 2, 1)
x_pos = [x for x in range(24)]
y_pos1 = [round(Pwt[idx], 3) for idx, y in enumerate(Pwt)]
plt.plot(x_pos, y_pos1)
plt.xlabel('t(h)')
plt.ylabel('Pwt(kWh)')

# Pdummy 그래프
plt.subplot(3, 2, 2)
y_pos2 = [round(P_dummy[idx], 3) for idx, y in enumerate(P_dummy)]
plt.plot(x_pos, y_pos2)
plt.xlabel('t(h)')
plt.ylabel('Pdummy(kWh)')

# Ppv 그래프
plt.subplot(3, 2, 3)
y_pos3 = [round(Ppv[idx], 3) for idx, y in enumerate(Ppv)]
plt.plot(x_pos, y_pos3)
plt.xlabel('t(h)')
plt.ylabel('Ppv(kWh)')

# Pbat 그래프
plt.subplot(3, 2, 4)
y_pos4 = [round(bc + bd, 3) for bc, bd in zip(P_bc, P_bd)]
plt.plot(x_pos, y_pos4)
plt.xlabel('t(h)')
plt.ylabel('Pbat(kWh)')

# Pload 그래프
plt.subplot(3, 2, 5)
y_pos5 = [round(Pload[idx], 3) for idx, y in enumerate(Pload)]
plt.plot(x_pos, y_pos5)
plt.xlabel('t(h)')
plt.ylabel('Pload(kWh)')

# SOC 그래프
plt.subplot(3, 2, 6)
y_pos6 = [round(SOC[idx], 3) for idx, y in enumerate(SOC)]
plt.plot(x_pos, y_pos6)
plt.xlabel('t(h)')
plt.ylabel('SOC(%)')

plt.show()