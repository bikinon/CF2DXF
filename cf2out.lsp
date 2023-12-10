;; (load "cf2out")(C:cf2out)
; Cycle through the drawing & save details of lines,Arcs & Circles to a CF2 format file
; Program works on a per layer baises
; Forme Blocks require a BIND and 4 or 5 EXPLODES due to Dims Text
; DIMS - Arrow heds need to be Arrow, closed - NOT Arrow, Filed (Our Std). Even then the layer & colour can change
; TG - 19 Oct 2019
;; GLOBALS: dScale term f5 spno
(defun C:cf2out ( / dwgnm layr ss_drw) ;  ll ur
	 (setq fln (getvar "dwgname"))	
	 (setq cf2fl (substr fln 1 (- (strlen fln) 4) ) ) ;
	
(setq FnPath "E:\\Exports\\") ; dir to save file into
(setq spno "46") ; DIMS / General normally Black line
(setq dScale 1) ;;100) 

  (command "TILEMODE" "0" ) ; Go to PaperSpace
  (command "ERASE" "ALL" "") ; Delete everything in Paperspace

  (command "TILEMODE" "1" ) ; Go to ModelSpace  
  (command "EXPLODE" "ALL") ; Multiple required on Dims
  (command "EXPLODE" "ALL") ; Enter no necessary
  (command "EXPLODE" "ALL")
  (command "EXPLODE" "ALL")
 
  (setq f5 (open (strcat FnPath cf2fl ".cf2") "w"))
  (setq ll (getvar "EXTMIN")
	    ur (getvar "EXTMAX") ); Many hardware systems require a 0,0 start point. It's simplest to move bottom Left to 0,0 if necessary. Just add a move after this code.

  (writeHeader)
  
  (setq layr (tblnext "LAYER" T)) ;; This needs to cycle layers
 (while layr
    (if (or (/= laynam "BVIEW") (/= laynam "BOARDER") )
		(writeData (cdr (assoc 2 layr)) )  ;(princ (strcat "\nLayer name: " (cdr (assoc 2 layr))))
    )
    (setq layr (tblnext "LAYER"))
 ) ; while 

  
; Tail end of the file
  (writeFooter)
  (close f5)
  
); C:cf2out
; <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
(defun writeData (laynam / ss tl  n ent itm obj q xs ys xe ye)
; Save drawing details to CF2 file - Plotting file Colour based
; 0 = Unrecognised_LineType0
; 1 = CUT
; 2 = CREASE
; 3 = PERF (must have 2nd Value) 3 = 10x10 perf,  8 = 3x3 perf, 10 = 6x6 perf (Mapped Auxilary Line)
; 7 = Dims
; 9 = Unrecognised_LineType9
; 10 = Balance Knives
; 13 = Bleeds
; 15 = Female Stripper
; 20 = Laser Fast Cut 
; 18 = Reverse Crease
; 19 = Reverse Score
; 22 = Laser Cut
; 23 = Unrecognised_LineType23
; 24 = Unrecognised_LineType24
; 40 = Unrecognised_LineType40
(setq laynam (strcase laynam)) ; make Upper case just in case
(setq spno "46") ; DIMS / General normally Black line -kept getting 2 ???

(if (= laynam "CUT") (setq spno "1")) ; Cut Knife - Tool numbers will vary
(if (= laynam "CUT2") (setq spno "1")) ; Shouldnt fire but sometimes does! Cut Knife - Tool numbers will vary
(if (= laynam "CREASE") (setq spno "2")) ; 2 Crease
(if (= laynam "CREASE2") (setq spno "2")) ; Shouldnt fire but sometimes does! 2 Crease
(if (= laynam "SLIT_SCORE") (setq spno "4")) ; 4 Score/half cut.
(if (= laynam "MATRIX") (setq spno "40")) ; 40 Rillma/Matrix designs.

(if (= laynam "ZIPPER") (setq spno "0")) ; 41 Zipper (3 parameters, length, gap, and angle). NOT TRANSLATING FOR ME
(if (= laynam "ZIPPEL") (setq spno "0")) ; 0 should give something

;; These need to be moved to ACM general cc & perf code
(if (= laynam "PERF") (setq spno "0")) ; 3 Perforation (2 parameters, cut and gap) NOT TRANSLATING FOR ME
(if (= laynam "CUTCRE") (setq spno "42")) ; 42 Cut/Crease (3 parameters, cut, crease, and land length).
(if (= laynam "CUTCRE_10X10") (setq spno "42")) ; 42 Cut/Crease (3 parameters, cut, crease, and land length).
(if (= laynam "CUTCRE_6X6") (setq spno "42")) ; 42 Cut/Crease (3 parameters, cut, crease, and land length).
(if (= laynam "CRE_8PT") (setq spno "2"))
(if (= laynam "CUTCRE_12X12") (setq spno "42")) 
(if (= laynam "CUTCRE_12X6") (setq spno "42")) 
(if (= laynam "PERF_10X10") (setq spno "0")) 
(if (= laynam "PERF_6X6") (setq spno "0")) 
(if (= laynam "PERF_3X3") (setq spno "0")) 
(if (= laynam "PERF_12X12") (setq spno "0")) 

(if (= laynam "CUT_DECKLE") (setq spno "45")) ; 45 Safety edge (2 parameters, height, and pitch)
(if (= laynam "DIMS") (setq spno "46")) ; 46 = Dimensions
(if (= laynam "PS_TAPES") (setq spno "46")) ; 46 = Dimensions
(if (= laynam "TEXT") (setq spno "46")) ; General normally Black line
(if (= laynam "DIEBROAD") (setq spno "46")) ; 20
(if (= laynam "EMBOSS") (setq spno "0")) ; 0 GIVES A GREY LINE
(if (= laynam "OTHER") (setq spno "46")) ; 46 = Dimensions BLACK LINE. Other comes from CF2 input

;;;(write-line (strcat spno term) f5)
  (setq ss (ssget "_X" (list (cons 8 laynam)))) ; Get all Entities
  (if (null ss)
    (princ "\nNo Entitys found on Layer.\n")
    (progn ; ELSE
		(setq n (1- (sslength ss)))		
		(while (>= n 0)
			(setq ent (entget (setq itm (ssname ss n)) '("*")) ; '("*") = Show Extended Data 210!
				obj (cdr (assoc 0 ent))
				q (cond
					((= obj "LINE") (progn
						(write-line (strcat "L,0," spno ",0," (rtos (* (cadr (assoc 10 ent)) dScale)  2 7) "," (rtos (* (caddr (assoc 10 ent)) dScale)  2 7) "," (rtos (* (cadr (assoc 11 ent)) dScale)  2 7) "," (rtos (* (caddr (assoc 11 ent)) dScale)  2 7) ",0,0") f5) ; Draw to End  "L,3," moved to "L,0," more general?				
						)) ; LINE
					((= obj "ARC")
							(arcout ent)
						) ; ARC
					((= obj "CIRCLE")
						(progn
;;A, p, t, at, sx, sy, ex, ey, cx, cy, =/-1, nbridges, wbridges
;;A,6,1,0,256.8721422,517.2520307,256.8721422,517.2520307,246.8726378,517.2520307,1,0,0.0000000						
							(setq cen (cdr (assoc 10 ent))      ; Centre XYZ
								  xc (car cen)					; DXF Centre X
								  yc (cadr cen)					; DXF Centre Y
								  rad (cdr (assoc 40 ent))      ; Radius
								  rotDir "1")
							(write-line (strcat "A,0," spno ",0," 
							 (rtos (+ xc rad) 2 7) "," (rtos yc 2 7) "," 
							 (rtos (+ xc rad) 2 7) "," (rtos yc 2 7) "," 
							 (rtos xc 2 7) "," (rtos yc 2 7) 
							 "," rotDir ",0,0")  f5) ; Move to Start / Center  f5) ; Move to Start / Center							
						) ) ; CIRCLE
					((= obj "TEXT")
						(progn
						; T, p, t, at, x, y, angle, height, width
						; textstring
						(setq txtang (* (cdr (assoc 50 ent)) 57.2957795147)) ; Text angle						

(setq ttmp (strcat "T,0," spno ",0," (rtos (* (cadr (assoc 10 ent)) dScale)  2 7) "," (rtos (* (caddr (assoc 10 ent)) dScale)  2 7) "," (rtos txtang) "," (rtos (* (cdr (assoc 40 ent)) dScale)  2 7) "," (rtos (* (cdr (assoc 40 ent)) dScale)  2 7) ))
						 (write-line ttmp  f5)
						 (write-line (cdr (assoc 1 ent))  f5) ; Text val
						) ; TEXT
					); progn	
					) ; cond
				n (1- n))) ; while
  ));progn IF
); writeData
; <><><><><><><><><><><><><><><><><><><><><>
; GLOBAL dScale term
(defun arcout (ent / aendx aendy Ang aStrX aStrY cen CenX CenY sAng eAng rad xc yc cErr rotDir)
    (setq cen (cdr (assoc 10 ent))      ; Centre XYZ
		  xc (car cen)					; DXF Centre X
		  yc (cadr cen)					; DXF Centre Y
          rad (cdr (assoc 40 ent))      ; Radius
          sAng (cdr (assoc 50 ent)) 	; Start Ang in Radians
          eAng (cdr (assoc 51 ent))
		  extrX (cdr (assoc 210 ent))	; X Extrusion (0 0 1) std, -1 on Mirrored
		  xxtr (caddr extrX)			; get the -1 or 1 val
		  extrY (cdr (assoc 220 ent))
		  cErr 0						; Error flag
		  rotDir "1"					; Rotation Direction
    )	; Start angle	
	(if (> eAng 359.9) (setq eAng 0)) ; fix for 360 degrees
	(if (> sAng 359.9) (setq sAng 0)) ; fix for 360 degrees

;; *** Fix for Reversed Arcs ********
(if (= xxtr -1) ; Y Mirror required
  (progn
	(setq xc (* xc -1))
	(setq atmp eAng)
	(setq eAng (YmirAng sAng))
	(setq sAng (YmirAng atmp))
) )
;; *********************	

	(setq aStrX (* (+ (* rad (cos sAng)) xc) dScale) 
	      aStrY (* (+ (* rad (sin sAng)) yc) dScale) 
	      aEndX (* (+ (* rad (cos eAng)) xc) dScale) 
	      aEndY (* (+ (* rad (sin eAng)) yc) dScale) 
	      CenX (* xc dScale) 
	      CenY (* yc dScale) ) ; end points not necessary but you never know when a version will need them
	(if (> sAng eAng)
	  (progn 
		(setq eAng (+ eAng 360))
		(setq Ang (dtr (- eAng (Rad2Deg sAng)) ) ) ; progs like Radians but HPGL & Humans like degrees
	) ; else
	(progn
		(setq Ang (- eAng sAng) )
	) )
	
	(write-line (strcat "A,0," spno ",0," (rtos aStrX  2 7) "," (rtos aStrY  2 7) "," (rtos aEndX  2 7) "," (rtos aEndY  2 7) "," (rtos CenX  2 7) "," (rtos CenY  2 7) "," rotDir ",0,0")  f5) ; Move to Start / Center
; Control points and end

) ; arcout
; <><><><><><><><><><><><><><><><><><><><><>
(defun writeHeader ( / )
	(write-line "$BOF" f5)
	(write-line "V2" f5)
	(write-line "ORDER" f5)
	(write-line "Name: " f5)
	(write-line "Address1: " f5)
	(write-line "Address2: " f5)
	(write-line "Address3: " f5)
	(write-line "Delivery date: " f5)
	(write-line "Board caliper:" f5)
	(write-line "Die required:" f5)
	(write-line "Rule the die:" f5)
	(write-line "Plywood thickness:" f5)
	(write-line "Cut rule height:" f5)
	(write-line "Crease rule height:" f5)
	(write-line "Score / half cut rule height:" f5)
	(write-line "Perf.rule height:" f5)
	(write-line "Number up:" f5)
	(write-line "Description:" f5)
	(write-line "END" f5)
	(write-line "MAIN,0800_125" f5)
	(write-line "UM" f5)
	(write-line "LL,-1447.301,-636.839," f5) ; Pull from Drawing
	(write-line "UR,890.644,962.686," f5)
	(write-line "SCALE,1,1," f5)
	(write-line "C,AGD,-1447.301,-636.839,0,1,1" f5)
	(write-line "END" f5)
	(write-line "SUB,AGD" f5)
 ); header
; <><><><><><><><><><><><><><><><><><><><><>
(defun writeFooter ( / )
	(write-line "END" f5)
	(write-line "$EOF" f5)
)
; <><><><><><><><><><><><><><><><><><><><><>
; Convert Degrees to Radians
(defun dtr (x)
	;define degrees to radians function
	(* pi (/ x 180.0))
	;divide the angle by 180 then
	;multiply the result by the constant PI
)	;end of function
; <><><><><><><><><><><><><><><><><><><><><>
; Convert Radians to Degrees
(DEFUN Rad2Deg (a / ret)
 ; (setq a (read (rtos a 2 5)) ; 5 decimal places
   (setq ret (* a 57.2957795147) )
  ret ; returned value
) ; defun
; <><><><><><><><><><><><><><><><><><><><><>
; Y Mirror Angle
(DEFUN YmirAng (a / ret)
 (if (< a 3.1416) ;5926536) ; 180 deg
	(setq ret (- 3.1415926536 a))
  )
  (if (> a 3.1416)
	(setq ret (+ (- 6.2831853072 a) 3.1415926536))
  )
  ret ; returned value
) ; YmirAng